<?php

namespace Foo\Bar\Block\Form;

use Magento\Framework\View\Element\UiComponent\Control\ButtonProviderInterface;

/**
 * Custom button.
 */
class MyCustom extends GenericButton implements ButtonProviderInterface
{
    /**
     * Retrieve Custom Button button settings.
     *
     * @return array
     */
    public function getButtonData(): array
    {
        return $this->wrapButtonSettings(
            'Custom Button',
            'custom',
            '',
            [],
            0
        );
    }
}
